ruleset {
    DuplicateImport
    ImportFromSamePackage
    UnusedImport
    UnnecessarySemicolon {
        doNotApplyToFileNames = "Transitionable.groovy"
    }
    MissingBlankLineAfterImports
    Println
    IllegalString {
        name = "JodaTimeUsed"
        string = "import org.joda.time"
        description = "Joda Time is deprecated, use Java 8 Time API instead."
    }
    IllegalRegex {
        name = "GuiceInjectTypeUsed"
        regex = /import com\.google\.inject\.Inject\s/
        description = "@Inject from com.google.inject and javax.inject are equivalent, use javax.inject.Inject for consistency."
    }
    IllegalRegex {
        name = "GuiceProviderTypeUsed"
        regex = /import com\.google\.inject\.Provider\s/
        description = "Provider from com.google.inject and javax.inject are equivalent, use javax.inject.Provider for consistency."
    }
}