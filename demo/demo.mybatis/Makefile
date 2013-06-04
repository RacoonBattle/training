all:
	mvn eclipse:eclipse -Dwtpversion=2.0 -DdownloadSources=true -DdownloadJavadocs=true
run:
	export MAVEN_OPTS="-server -Xms512m -Xmx1536m -XX:+UseFastAccessorMethods" && mvn jetty:run
dep:
	mvn dependency:tree -Ddetail
clean:
	mvn eclipse:clean
	mvn clean
	rm -rf .settings