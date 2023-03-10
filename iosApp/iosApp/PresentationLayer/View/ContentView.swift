import SwiftUI
import MultiPlatformLibrary
import mokoMvvmFlowSwiftUI
import MultiPlatformLibrarySwift
import Combine
import CoreLocation

struct ContentView: View {
    @ObservedObject var viewModel : WeatherViewModel = CoreInjector.init().provideWeatherViewModel()
    @ObservedObject var locationManager = LocationManager()
    var body: some View {
        let state : WeatherState = viewModel.state(\.state, equals: {$0 == $1}, mapper: {$0 as WeatherState})
        ScrollView() {
            VStack {
                switch LoadableStateKs(state.locationWithDate) {
                case .success(let locationState):
                    HStack(alignment: .center) {
                        Spacer(minLength: 20)
                        VStack(alignment: .leading) {
                            Text("\(locationState.data!.city!), \(locationState.data!.country!)")
                                .font(.custom("Inter-Bold", size: 22))
                                .lineLimit(3)
                            Text("\(locationState.data!.formattedDate)")
                                .padding(.top, 8)
                        }
                        Spacer()
                            .padding(.leading, 12)
                    }
                default:
                    EmptyView()
                }
                switch LoadableStateKs(state.currentWeatherState) {
                case .success(let success):
                    let data = success.data!
                    HStack(alignment: .center) {
                        Image(data.type.name)
                            .resizable()
                            .scaledToFit()
                            .shadow(radius: 10)
                            .frame(width: 80, height: 80)
                            .padding(20)
                        VStack {
                            Text("\(data.temperature)")
                                .font(.custom("Inter-Bold", size: 43))
                                .padding(0)
                            Text(typeText(type: data.type))
                        }
                    }
                    paramsView(paransDescription: "\(data.rainFall) cm", imageName: "rainFallIcon", type: "Rain Fall")
                    paramsView(paransDescription: "\(data.windSpeed) km/h", imageName: "windIcon", type: "Wind")
                    paramsView(paransDescription: "\(data.humidity) %", imageName: "humidityIcon", type: "Humidity")
                default:
                    EmptyView()
                }
                Spacer().frame(height: 16)
                futureWeatherCollection(state: LoadableStateKs(state.futureWeatherState))
            }.frame(maxWidth: .infinity)
        }
        .background(LinearGradient(colors: [.white, .orange], startPoint: .topLeading, endPoint: .bottomTrailing))
        .onChange(
            of: locationManager.authorizationStatus,
            perform: {
                if($0 == .authorizedWhenInUse || $0 == .authorizedAlways) {
                    if let coordinate = locationManager.locationManager.location?.coordinate {
                        viewModel.loadInitialData(latitude: coordinate.latitude, longitude: coordinate.longitude, locale: "RU_ru")
                    }
                }
            }
        )
    }
        

    func paramsView(paransDescription: String, imageName: String, type: String) -> some View {
            ZStack {
                HStack {
                    Image(imageName)
                        .resizable()
                        .scaledToFit()
                        .shadow(radius: 10)
                        .frame(width: 40, height: 60)
                        .padding(.leading, 20)
                        .padding(.top, 10)
                        .padding(.bottom, 10)
                    Text(type)
                        .font(.custom("Inter-Ligh", size: 20))
                        .padding(.trailing, 40)
                    Spacer()
                    Text(paransDescription)
                        .font(.custom("Inter-Ligh", size: 20))
                        .padding(.trailing, 40)
                }
                .background(Color.white)
                .opacity(0.7)
            }
            .cornerRadius(10)
            .aspectRatio(contentMode: .fill)
            .padding(.leading, 20)
            .padding(.trailing, 20)
    }
    
    func futureWeatherCollection(state: LoadableStateKs<WeatherState.FutureWeatherState>) -> some View {
        Group {
            switch state {
            case .success(let futureState):
                ScrollView(.horizontal, showsIndicators: false) {
                    HStack(spacing: 10) {
                        let items = futureState.data!.items
                        ForEach(0..<items.count, id: \.self) { index in
                            futureTimeeCell(item: items[index])
                        }
                    }
                    .padding(.leading, 16)
                }
            default:
                EmptyView()
            }
        }
    }

    func futureTimeeCell(item: WeatherState.FutureWeatherItem) -> some View {
        ZStack {
            VStack {
                Text(item.time)
                    .foregroundColor(.black)
                    .font(.custom("Inter-Light", size: 11))
                    .padding(.top, 8)
                Image(item.type.name)
                    .resizable()
                    .scaledToFit()
                    .shadow(radius: 10)
                    .frame(width: 20, height: 20)
                    .padding(.top, 0)
                Text("\(item.temperature)")
                    .foregroundColor(.black)
                    .font(.custom("Inter-Bold", size: 11))
                    .padding(.top, 0)
                    .padding(.bottom, 8)
            }
            .frame(width: 45, height: 90)
            .background(Color.white)
            .opacity(0.7)
            .cornerRadius(20)
        }

    }
}

private func typeText(type: Weather.Type_) -> String {
    switch type {
    case .clear:
        return "Clear"
    case .cloudy:
        return "Cloudy"
    case .cloudyRain:
        return "Rain"
    case .partyCloudy:
        return "Party cloudy"
    case .partyCloudyRain:
        return "Rainy"
    default:
        return "Clear"
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
