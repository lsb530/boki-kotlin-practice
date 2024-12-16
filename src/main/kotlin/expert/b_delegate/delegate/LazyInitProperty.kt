package expert.b_delegate.delegate

import kotlin.reflect.KProperty

// 위임패턴 사용
class LazyInitPropertyPerson {
    private val delegateProperty = LazyInitProperty {
        Thread.sleep(2000)
        "김수한무"
    }

    val name: String
        get() = delegateProperty.value
}

class LazyInitPropertyPerson2 {
    private val delegateProperty by LazyInitProperty {
        Thread.sleep(2000)
        "김수한무"
    }
}

class LazyInitProperty<T>(val init: () -> T) {
    private var _value: T? = null
    val value: T
        get() {
            if (_value == null) {
                this._value = init()
            }
            return _value!!
        }

    operator fun getValue(thisRef: Any, property: KProperty<*>): T {
        return value
    }
}
