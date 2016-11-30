Configurações necessárias:

Servidor Jboss AS 7.1

No arquivo standalone.xml

Adicionar Driver:

	<driver name="com.mysql" module="com.mysql">
	    <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
	</driver>

Adicionar DataSource:

	<datasource jndi-name="java:/livrariaDS" pool-name="livrariaDS" enabled="true" use-java-context="true">
	    <connection-url>jdbc:mysql://localhost:3306/livraria</connection-url>
	    <driver>com.mysql</driver>
	    <pool>
	        <min-pool-size>10</min-pool-size>
	        <max-pool-size>100</max-pool-size>
	        <prefill>true</prefill>
	    </pool>
	    <security>
	        <user-name>root</user-name>
	        <password></password>
	    </security>
	</datasource>