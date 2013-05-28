ProxyFactory factory = new ProxyFactory(new SimplePojo());
factory.addInterface(Pojo.class);
factory.addAdvice(new RetryAdvice());

Pojo pojo = (Pojo) factory.getProxy();
      
// this is a method call on the proxy!
pojo.foo();
