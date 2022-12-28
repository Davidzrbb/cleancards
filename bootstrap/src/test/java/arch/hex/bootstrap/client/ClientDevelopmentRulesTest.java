package arch.hex.bootstrap.client;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static arch.hex.bootstrap.PackagesAndLayers.BOOTSTRAP_PACKAGE;
import static arch.hex.bootstrap.PackagesAndLayers.CLIENT_PACKAGE;
import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
        packages = "arch.hex.client",
        cacheMode = FOREVER,
        importOptions = {ImportOption.DoNotIncludeTests.class})
public class ClientDevelopmentRulesTest {

    @ArchTest
    public static final ArchRule CLIENT_DEVELOPMENT_RULE =
            classes()
                    .that()
                    .resideInAPackage(CLIENT_PACKAGE)
                    .should()
                    .onlyHaveDependentClassesThat()
                    .resideInAnyPackage(CLIENT_PACKAGE, BOOTSTRAP_PACKAGE)
                    .andShould()
                    .onlyBeAccessed()
                    .byClassesThat()
                    .resideInAnyPackage(BOOTSTRAP_PACKAGE, CLIENT_PACKAGE)
                    .andShould()
                    .onlyHaveDependentClassesThat()
                    .resideInAnyPackage(BOOTSTRAP_PACKAGE, CLIENT_PACKAGE);
}
