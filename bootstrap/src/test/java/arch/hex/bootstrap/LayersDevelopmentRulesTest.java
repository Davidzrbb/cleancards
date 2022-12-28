package arch.hex.bootstrap;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static arch.hex.bootstrap.PackagesAndLayers.*;
import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;


@AnalyzeClasses(
        packages = "arch",
        cacheMode = FOREVER,
        importOptions = {ImportOption.DoNotIncludeTests.class})
public class LayersDevelopmentRulesTest {
    @ArchTest
    public static final ArchRule LAYERS_DEVELOPMENT_RULE =
            layeredArchitecture()
                    .layer(DOMAIN_LAYER)
                    .definedBy(DOMAIN_PACKAGE)
                    .layer(BOOTSTRAP_LAYER)
                    .definedBy(BOOTSTRAP_PACKAGE)
                    .layer(CLIENT_LAYER)
                    .definedBy(CLIENT_PACKAGE)
                    .layer(SERVER_LAYER)
                    .definedBy(SERVER_PACKAGE);

}
