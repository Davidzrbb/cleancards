package arch.hex.bootstrap.domain;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static arch.hex.bootstrap.PackagesAndLayers.*;
import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(
        packages = "arch.hex.domain",
        cacheMode = FOREVER,
        importOptions = {ImportOption.DoNotIncludeTests.class})
public class DomainDevelopmentRulesTest {

    private static final String[] allowedPackages = {
            JAVA_PACKAGE, DOMAIN_PACKAGE, LOMBOK_PACKAGE, VAVR_PACKAGE, SLF4J
    };

    private static final String[] allowedAccessors = {
            DOMAIN_PACKAGE, BOOTSTRAP_PACKAGE, CLIENT_PACKAGE, SERVER_PACKAGE
    };

    @ArchTest
    public static final ArchRule DOMAIN_DEVELOPMENT_RULE =
            classes()
                    .that().resideInAPackage(DOMAIN_PACKAGE)
                    .should().onlyBeAccessed().byAnyPackage(allowedAccessors)
                    .andShould().onlyHaveDependentClassesThat().resideInAnyPackage(allowedPackages);
}