package com.ofbizian.buildconfig;

import java.util.List;

import com.google.common.collect.ImmutableList;

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.extensions.ConfigMap;

public class ReplicationControllerKubernetesModelProcessor {

        public void on(ReplicationControllerSpecBuilder builder) {
        
            builder.editTemplate()
            	   .editSpec()
            	   .withContainers(getContainers())
            	   .withVolumes(getVolumes())
            	   .endSpec().endTemplate().build();
    }

        private List<Volume> getVolumes(){   	
            Volume vol = new Volume();
            vol.setName("config-volume");
            ConfigMap configMap = new ConfigMap();
            configMap.setKind("whatKInd");
            configMap.setAdditionalProperty("name", "demo");
            //Test doesnt'Work
            vol.setAdditionalProperty("configMap", configMap);
            return new ImmutableList.Builder<Volume>().add(vol).build();

        }
        private Container getContainers() {
            Container container = new Container();
            container.setVolumeMounts(getVolumeMounts());
            return container;
        }
        
		private List<VolumeMount> getVolumeMounts() {
			VolumeMount vol = new VolumeMount();
			vol.setName("config-volume");
			vol.setMountPath("/etc/config");
			return new ImmutableList.Builder<VolumeMount>().add(vol).build();
		}

}
