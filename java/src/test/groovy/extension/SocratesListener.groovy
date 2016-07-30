package extension

import org.spockframework.runtime.AbstractRunListener
import org.spockframework.runtime.model.FeatureInfo

class SocratesListener extends AbstractRunListener {

    @Override
    void beforeFeature(FeatureInfo feature) {
        println "Socrates is fun!"
    }

}
