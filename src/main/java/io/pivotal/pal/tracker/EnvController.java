package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    private Map<String, String> envMap = new HashMap();

//    PORT
//            MEMORY_LIMIT
//    CF_INSTANCE_INDEX
//            CF_INSTANCE_ADDR

    public EnvController (@Value("${port:NOT SET}") String port,
        @Value("${memory.limit:NOT SET}") String memoryLimit,
        @Value("${cf.instance.index: NOT SET}") String cfInstanceIndex,
        @Value("${cf.instance.addr: NOT SET}") String cfInstanceAddr
    ) {
        this.envMap.put("PORT", port);
        this.envMap.put("MEMORY_LIMIT", memoryLimit);
        this.envMap.put("CF_INSTANCE_INDEX", cfInstanceIndex);
        this.envMap.put("CF_INSTANCE_ADDR", cfInstanceAddr);
    }

    @GetMapping("/env")
    public Map<String, String> getEnv () {
        return this.envMap;
    }

}
