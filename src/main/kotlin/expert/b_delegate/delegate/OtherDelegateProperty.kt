package expert.b_delegate.delegate

import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

fun main() {
    val person = CustomDelegatePropertyPerson()
}

class LazyInitPropertyInterfaceImpl<T>(val init: () -> T) : ReadOnlyProperty<Any, T> {
    private var _value: T? = null
    private val value: T
        get() {
            if (_value == null) {
                this._value = init()
            }
            return _value!!
        }

    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value
    }
}

class LazyInitPropertyAnonymous {
    private var isGreen: Boolean = false
    val status: String by object : ReadOnlyProperty<LazyInitPropertyAnonymous, String> {
        override fun getValue(thisRef: LazyInitPropertyAnonymous, property: KProperty<*>): String {
            return if (isGreen) {
                isGreen = true
                "Happy"
            } else {
                isGreen = false
                "Sad"
            }
        }
    }
}

class CustomDelegatePropertyPerson {
    val name1 by CustomDelegateProperty("이승복")
    val country1 by CustomDelegateProperty("한국")

    val name2 by CustomDelegateProperty2("이승복", "name")   // O
    // val country2 by CustomDelegateProperty2("한국", "country") // X

    val name by DelegateProvider("이승복")
    val country by DelegateProvider("한국") // X
}

class CustomDelegateProperty(
    private val initValue: String,
) : ReadOnlyProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return initValue
    }
}

// property명이 name일때만 정상동작
class CustomDelegateProperty2(
    private val initValue: String,
    propertyName: String,
) : ReadOnlyProperty<Any, String> {
    init {
        if (propertyName != "name") {
            throw IllegalArgumentException("name에만 사용 가능합니다!")
        }
    }

    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return initValue
    }
}

class DelegateProvider(
    private val initValue: String
) : PropertyDelegateProvider<Any, CustomDelegateProperty> {
    override fun provideDelegate(thisRef: Any, property: KProperty<*>): CustomDelegateProperty {
        if (property.name != "name") {
            throw IllegalArgumentException("${property.name}은 안돼요! name만 연결 가능합니다!")
        }
        return CustomDelegateProperty(initValue)
    }
}
