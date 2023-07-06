package com.emmanuel_rono.fav_dish.Data.ViewModel

import androidx.lifecycle.*
import com.emmanuel_rono.fav_dish.Data.Database.favDishRepository
import com.emmanuel_rono.fav_dish.Data.fav_Dish
import kotlinx.coroutines.launch

class viewModel(val repository:favDishRepository):ViewModel() {
        fun insert(dish:fav_Dish)= viewModelScope.launch {
            repository.insertFavDishData(dish)
        }
    val alldisList:LiveData<List<fav_Dish>> = repository.allDishesList.asLiveData()

}

class viewModelProviderFactory(private val repository: favDishRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        //check modelClass if assignable fom viewmodel
        if (modelClass.isAssignableFrom(viewModel::class.java)) {
            return viewModel(repository) as T
        }
        throw IllegalAccessException("Uknown ViewModel Class")

    }
}