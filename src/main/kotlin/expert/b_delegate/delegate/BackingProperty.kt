package expert.b_delegate.delegate

class BackingPropertyPerson {
    // name과 대응되는, 외부로 드러나지 않는 프로퍼티 : backing property
    private var _name: String? = null
    val name: String
        get() {
            if (_name == null) {
                Thread.sleep(2000)
                this._name = "김수한무"
            }
            return _name!!
        }
}
