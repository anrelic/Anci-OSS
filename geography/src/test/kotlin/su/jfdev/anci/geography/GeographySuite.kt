package su.jfdev.anci.geography

import org.junit.runner.*
import org.junit.runners.*
import org.junit.runners.Suite.*
import su.jfdev.anci.geography.services.*

@SuiteClasses(RegionSuite::class,
              GeoTest::class,
              IntRegionImplIteratorSpec::class,
              Vec3iStoreTest::class)
@RunWith(Suite::class)
class GeographySuite {
}