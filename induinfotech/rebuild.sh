rm -rf $TOMCAT_HOME/webapps/induinfotech $TOMCAT_HOME/webapps/induinfotech.war
mvn clean package -DskipTests
ln -s /Users/rmoparth/Workspace/induinfotech/target/ROOT.war $TOMCAT_HOME/webapps/induinfotech.war
