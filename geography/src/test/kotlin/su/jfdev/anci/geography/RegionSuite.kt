package su.jfdev.anci.geography

import org.junit.runner.*
import org.junit.runners.*
import org.junit.runners.Suite.*
import su.jfdev.anci.geography.region.*

@SuiteClasses(AnyRegionSpec::class,
              EmptyRegionSpec::class,
              LineRegionSpec::class,
              SinglePointRegionSpec::class)
@RunWith(Suite::class)
class RegionSuite