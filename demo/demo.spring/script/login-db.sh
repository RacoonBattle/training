URL=jdbc:h2:tcp://localhost:9081/mem:demo
USER=sa
PASSWORD=sa

java -cp ~/.m2/repository/com/h2database/h2/1.3.174/h2-1.3.174.jar org.h2.tools.Console -url $URL -user $USER -password $PASSWORD
