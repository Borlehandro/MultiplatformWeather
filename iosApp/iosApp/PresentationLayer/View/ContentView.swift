import SwiftUI
import core

struct ContentView: View {
    let greet = "Test"
    @StateObject var viewModel = MainViewModel()
    var body: some View {
        ScrollView() {
            VStack {
                VStack(alignment: .leading) {
                    Text(viewModel.location)
                        .font(.custom("Inter-Bold", size: 30))
                    Text("\(Date().formatted(.dateTime.day().month()))")
                }
                HStack(alignment: .center) {
                    Image("rainyImage")
                        .resizable()
                        .scaledToFit()
                        .shadow(radius: 10)
                        .frame(width: 180, height: 180)
                    VStack {
                        Text(viewModel.temperature)
                            .font(.custom("Inter-Bold", size: 43))
                            .padding(0)
                        Text(viewModel.type)
                    }
                }
                paramsView(paransDescription: viewModel.windSpeed + " " + "km/h", imageName: "windIcon")
                paramsView(paransDescription: viewModel.rainFall + " " + "cm", imageName: "rainFallIcon")
            }.frame(maxWidth: .infinity)
        }
        .background(Color.mint.ignoresSafeArea())
    }
    
    func paramsView(paransDescription: String, imageName: String) -> some View {
            ZStack {
                HStack {
                    Image(imageName)
                        .resizable()
                        .scaledToFit()
                        .shadow(radius: 10)
                        .frame(width: 80, height: 80)
                        .padding(.leading, 20)
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
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
