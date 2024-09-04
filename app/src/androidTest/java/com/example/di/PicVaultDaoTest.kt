package com.example.di

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.picvault.data.local.dao.PicVaultDao
import com.example.picvault.data.local.database.LocalDatabase
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
class PicVaultDaoTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test")
    lateinit var inMemoryDb: LocalDatabase

    @Inject
    lateinit var picVaultDao: PicVaultDao


    @Before
    fun setUp() {
        hiltRule.inject()
        picVaultDao = inMemoryDb.picVaultDao

    }

    @After
    fun tearDown() {
        inMemoryDb.close()
    }
}