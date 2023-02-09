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
    @Published var location = ""
    @Published var futureWeatherParams: [String : Any] = [:]
    @Published var futureWeatherKeys: [String] = []
    @Published var futureWeatherTemperature: [String] = []
    @Published var futureWeatherType: [String] = []
    
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
        
        CoreInjector.init().provideWeatherInteractor().getFutureWeather(location: getLocation(), timeZone: getCurrentTimeZone()) { [self] futureWeather, error in
            guard let futureWeather = futureWeather else { return }
            self.futureWeatherParams = futureWeather
            for i in futureWeather {
                self.futureWeatherKeys.append(i.key)
            }
            
            self.futureWeatherKeys.forEach {
                futureWeatherType.append((futureWeather[$0]?.type.name)!)
                futureWeatherTemperature.append("\((futureWeather[$0]?.temperature)!)")
            }

        }
        
        location = getCurrentTimeZone()
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
