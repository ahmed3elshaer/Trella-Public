package com.trella.ui

import com.trella.data.HomeApi
import com.trella.data.ShipmentsMemorySource
import com.trella.data.local.ShipmentsDao
import com.trella.utils.DataGenerator
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when`


class HomeRepositoryTest {
    @Mock
    private lateinit var repository: HomeRepository

    @Mock
    private lateinit var homeApi: HomeApi
    @Mock
    private lateinit var shipmentsDao: ShipmentsDao
    @Mock
    private lateinit var shipmentsMemorySource: ShipmentsMemorySource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        repository = HomeRepository(
            homeApi,
            shipmentsDao,
            shipmentsMemorySource
        )
    }

    @Test
    fun validateErrorBypassFromRemoteAndMemoryEmits() {

        errorInRemote()
        emptyAndCompleteDiskCache()
        fillMemoryCache()// with four elements

        repository.getShipments(null, null)
            .test().apply {
                assertNoErrors()
                assertComplete()
                assertValue { it.size == 4 }
            }.dispose()
    }

    @Test
    fun validateErrorBypassFromRemoteAndDiskEmits() {

        errorInRemote() // connection error
        fillDiskCache() // with two elements
        emptyAndCompleteMemoryCache() // empty

        repository.getShipments(null, null)
            .test().apply {
                assertNoErrors()
                assertComplete()
                assertValue { it.size == 2 }
            }.dispose()
    }

    @Test
    fun validateErrorBypassFromRemoteAndMemoryEmitsWhileDiskIsCached() {

        errorInRemote() // connection error
        fillDiskCache() // with two elements
        fillMemoryCache() // with four elements

        repository.getShipments(null, null)
            .test().apply {
                assertNoErrors()
                assertComplete()
                assertValue { it.size == 4 }
            }.dispose()
    }

    @Test
    fun remoteEmitsFirstWithDiskAndMemoryCached() {

        returnInRemote() // with three elements
        fillDiskCache() // with two elements
        fillMemoryCache() // with four elements

        repository.getShipments(null, null)
            .test().apply {
                assertNoErrors()
                assertComplete()
                assertValue { it.size == 3 }
            }.dispose()
    }

    private fun emptyAndCompleteMemoryCache() {
        `when`(shipmentsMemorySource.getShipments()).thenReturn(Observable.empty())

    }

    private fun fillMemoryCache() {
        `when`(shipmentsMemorySource.getShipments()).thenReturn(Observable.just(DataGenerator.fourEntityShipments()))

    }

    private fun emptyAndCompleteDiskCache() {
        `when`(shipmentsDao.allShipments()).thenReturn(Observable.empty())


    }

    private fun fillDiskCache() {
        `when`(shipmentsDao.allShipments()).thenReturn(Observable.just(DataGenerator.twoEntityShipments()))
    }

    private fun errorInRemote() {
        `when`(
            homeApi.getShipments(null, null)
        ).thenReturn(Observable.error(DataGenerator.throwable))


    }

    private fun returnInRemote() {
        `when`(
            homeApi.getShipments(null, null)
        ).thenReturn(Observable.just(DataGenerator.threeEntityShipments()))


    }
}