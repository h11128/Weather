# Weather
This is a sample weather project with one activity and two tab.  First tab is a fragment with text of current weather. Second tab is a fragment with a recyclerview of future 10 days weather condition. 

This project use dagger, rxjava2, retrofit, MVVM design pattern, data binding, recycler view. 

App will trigger network call onResume or pull down refresh, stop the network call onPause. The cache is not implmented due to time limit. 

Api refrence: the forecast and current api are used from  https://www.weatherapi.com/
