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
                        Text(viewModel.location)
                            .font(.custom("Inter-Bold", size: 30))
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
                paramsView(paransDescription: viewModel.windSpeed + " " + "km/h", imageName: "windIcon")
                paramsView(paransDescription: viewModel.rainFall + " " + "cm", imageName: "rainFallIcon")
                paramsView(paransDescription: viewModel.humidity + " " + "%", imageName: "humidityIcon")
                futureWeatherCollection()
            }.frame(maxWidth: .infinity)
        }
        .background(LinearGradient(colors: [.white, .orange], startPoint: .topLeading, endPoint: .bottomTrailing))
    }
    
    func paramsView(paransDescription: String, imageName: String) -> some View {
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
                    Text(viewModel.futureWeatherKeys[index])
                        .foregroundColor(.black)
                        .font(.custom("Inter-Bold", size: 12))
                        .frame(width: 32, height: 56)
                        .background(Color.white)
                        .opacity(0.7)
                        .cornerRadius(20)
                }
                .padding(.top, 30)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
