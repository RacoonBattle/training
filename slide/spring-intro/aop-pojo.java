public class SimplePojo implements Pojo {

    public void foo() {
        // direct call on the 'this' reference
        this.bar();
    }
   
    public void bar() {
        // some logic...
    }
}
