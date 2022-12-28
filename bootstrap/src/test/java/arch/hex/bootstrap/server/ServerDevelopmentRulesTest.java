package arch.hex.bootstrap.server;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static arch.hex.bootstrap.PackagesAndLayers.BOOTSTRAP_PACKAGE;
import static arch.hex.bootstrap.PackagesAndLayers.SERVER_PACKAGE;
import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
        packages = "arch.hex.server",
        cacheMode = FOREVER,
        importOptions = {ImportOption.DoNotIncludeTests.class})
public class ServerDevelopmentRulesTest {

    @ArchTest
    public static final ArchRule SERVER_DEVELOPMENT_RULE =
            classes()
                    .that()
                    .resideInAPackage(SERVER_PACKAGE)
                    .should()
                    .onlyHaveDependentClassesThat()
                    .resideInAnyPackage(SERVER_PACKAGE, BOOTSTRAP_PACKAGE)
                    .andShould()
                    .onlyBeAccessed()
                    .byAnyPackage(BOOTSTRAP_PACKAGE, SERVER_PACKAGE);
}
