import SwiftUI
import core

struct ContentView: View {
    let greet = "Test"
    @StateObject var viewModel = MainViewModel()
    var body: some View {
        ScrollView() {
            VStack {
                HStack(alignment: .center) {
                    Spacer(minLength: 20)
                    VStack(alignment: .leading) {
                        Text("Новосибирск")
                            .font(.custom("Inter-Bold", size: 22))
                            .padding(20)
                            .lineLimit(1)
                        Text("\(Date().formatted(.dateTime.day().month()))")
                    }
                    Spacer()
                        .padding(.leading, 20)
                }
                HStack(alignment: .center) {
                    Image(viewModel.type)
                        .resizable()
                        .scaledToFit()
                        .shadow(radius: 10)
                        .frame(width: 80, height: 80)
                        .padding(20)
                    VStack {
                        Text(viewModel.temperature)
                            .font(.custom("Inter-Bold", size: 43))
                            .padding(0)
                        Text(viewModel.type)
                    }
                }
                paramsView(paransDescription: viewModel.windSpeed + " " + "km/h", imageName: "windIcon", type: "Wind")
                paramsView(paransDescription: viewModel.rainFall + " " + "cm", imageName: "rainFallIcon", type: "RainFall")
                paramsView(paransDescription: viewModel.humidity + " " + "%", imageName: "humidityIcon", type: "Humidity")
                futureWeatherCollection()
            }.frame(maxWidth: .infinity)
        }
        .background(LinearGradient(colors: [.white, .orange], startPoint: .topLeading, endPoint: .bottomTrailing))
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
    
    func futureWeatherCollection() -> some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: 10) {
                ForEach(0..<viewModel.futureWeatherKeys.count, id: \.self) { index in

                    futureTimeeCell(index: index)
                }
                .padding(.top, 30)
            }.padding(.leading, 20)
        }
    }
    
    func futureTimeeCell(index: Int) -> some View {
        ZStack {
            VStack {
                Text(viewModel.futureWeatherKeys[index])
                    .foregroundColor(.black)
                    .font(.custom("Inter-Light", size: 7))
                    .padding(.top, 8)
                if !viewModel.futureWeatherType.isEmpty && index <= viewModel.futureWeatherType.count - 1 {
                    Image(viewModel.futureWeatherType[index])
                        .resizable()
                        .scaledToFit()
                        .shadow(radius: 10)
                        .frame(width: 20, height: 20)
                        .padding(.top, 0)
                }
                if !viewModel.futureWeatherTemperature.isEmpty && index <= viewModel.futureWeatherTemperature.count - 1  {
                    Text(viewModel.futureWeatherTemperature[index])
                        .foregroundColor(.black)
                        .font(.custom("Inter-Bold", size: 7))
                        .padding(.top, 0)
                        .padding(.bottom, 8)
                }
            }
            .frame(width: 32, height: 60)
            .background(Color.white)
            .opacity(0.7)
            .cornerRadius(20)
        }
        
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
