//
//  MainViewModel.swift
//  iosApp
//
//  Created by Иван Нескин on 06.02.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import core
import CoreLocation

final class MainViewModel: ObservableObject {
    @Published var type = ""
    @Published var temperature = ""
    @Published var rainFall = ""
    @Published var windSpeed = ""
    @Published var humidity = ""
    init() {
        setData()
    }
    
    private func setData() {
        CoreInjector.init().provideWeatherInteractor().getCurrentWeather(location: getLocation(), timeZone: getCurrentTimeZone()) { weather, error in
            guard let weather = weather else { return }
            self.temperature = "\(weather.temperature)"
            self.rainFall = "\(weather.rainFall)"
            self.windSpeed = "\(weather.windSpeed)"
            self.humidity = "\(weather.humidity)"
            self.type = weather.type.name
        }
    }
    
    private func getLocation() -> Location {
        let currentLoccation = CLLocation()
        return Location(latitude: currentLoccation.coordinate.latitude,
                        longitude: currentLoccation.coordinate.longitude)
    }
    
    private func getCurrentTimeZone() -> String {
        TimeZone.current.identifier
    }
}
