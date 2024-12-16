package expert.b_delegate.delegate

class ByLazyPerson {
    val name: String by lazy {
        Thread.sleep(2000)
        "김수한무"
    }
}
