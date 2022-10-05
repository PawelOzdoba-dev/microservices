package pl.app.product.config;

import com.hazelcast.config.*;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class HazelcastConfig {

    @Bean
    public Config configHazelcast() {
        return new Config()
                .setInstanceName("hazelcast-instant") //nazwa instancji
                .addMapConfig(new MapConfig()
                        .setName("product") //nazwa naszej mapy
                        .setEvictionConfig(new EvictionConfig()
                                .setEvictionPolicy(EvictionPolicy.LRU) //usuwamy cache dla najstarszych obiektow
                                .setSize(100) //maksymala ilość obiektów w cache
                                .setMaxSizePolicy(MaxSizePolicy.FREE_HEAP_SIZE) //pozwala przechowywać obiekty dopóki jest pamięć na maszynie jvm
                        )
                        .setTimeToLiveSeconds(60 * 60 * 24)//ile czasu obiekt zostanie przechwytywany w cache
                );
//Dodać najszybszą implementację serializacji: IdentifiedDataSerializable, https://hazelcast.com/blog/comparing-serialization-methods/

//        zipkin aplikacja która służy do wyszukiwania komunikacji w mikroserwisach.
//        Przy użyciu trac id możemy prześliedzić całą trase żądania.

//        elasticsearch używamy do przetrzymywania informacji o produktach w koszyku.
    }
}
