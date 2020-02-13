package com.trella.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ahmed3elshaer.geosquar.utils.LiveDataTestUtil
import com.trella.common.viewstate.BaseViewState
import com.trella.domain.GetShipmentsUseCase
import com.trella.utils.DataGenerator
import io.reactivex.Maybe
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class HomeViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var viewModel: HomeViewModel
    @Mock
    private lateinit var getShipmentsUseCase: GetShipmentsUseCase
    private val shipmentsList = DataGenerator.getShipments()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = HomeViewModel(getShipmentsUseCase)
    }


    @Test
    fun ShouldReturnShipmentsAndNoNearby() {
        `when`(getShipmentsUseCase.getAllShipments(null, null)).thenReturn(
            Maybe.just(
                shipmentsList
            )
        )
        viewModel.getShipments(null, null)
        assertEquals(
            LiveDataTestUtil.getValue(viewModel.viewState).status,
            BaseViewState.Status.SUCCESS
        )
        assertEquals(LiveDataTestUtil.getValue(viewModel.viewState).error, null)
        assertEquals(LiveDataTestUtil.getValue(viewModel.viewState).payload.shipments.size, 3)
        assertFalse(LiveDataTestUtil.getValue(viewModel.viewState).payload.isNearby)
    }
}