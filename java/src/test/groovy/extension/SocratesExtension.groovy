package extension

import org.spockframework.runtime.extension.AbstractAnnotationDrivenExtension
import org.spockframework.runtime.model.SpecInfo


class SocratesExtension extends AbstractAnnotationDrivenExtension<Socrates> {
    private SocratesListener interceptor = new SocratesListener()


    @Override
    void visitSpecAnnotation(Socrates annotation, SpecInfo spec) {
        spec.addListener(interceptor)
    }

}
