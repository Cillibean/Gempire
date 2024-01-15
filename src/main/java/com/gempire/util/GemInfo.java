package com.gempire.util;

import java.util.HashMap;

public class GemInfo {
    public HashMap<String, Object> map = new HashMap<>();

    public void setMap(){
        int[] rubyResources = new int[4];
        map.put("ruby", rubyResources);
    }

}
