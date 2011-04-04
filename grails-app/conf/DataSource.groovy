dataSource {
    pooled = true
	driverClassName = "org.postgresql.Driver"
	username = "tomcat"
	password = "tomcat00"
    dbCreate = "update" // create, create-drop, update
    url = 'jdbc:postgresql:fitapps'
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = 'net.sf.ehcache.hibernate.EhCacheProvider'
    dialect='org.hibernate.dialect.PostgreSQLDialect'
    show_sql=true
}
// environment specific settings
environments {
    development {
        dataSource {
            //dbCreate = "create-drop" // one of 'create', 'create-drop','update'
            //url = "jdbc:hsqldb:mem:devDB"
        }
    }
    test {
        dataSource {
            //dbCreate = "update"
            //url = "jdbc:hsqldb:mem:testDb"
        }
    }
    production {
        dataSource {
            //dbCreate = "update"
            //url = "jdbc:hsqldb:file:prodDb;shutdown=true"
        }
    }
}
