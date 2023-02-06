import SwiftUI
import core

struct ContentView: View {
    let greet = "Test"
    @StateObject var viewModel = MainViewModel()
    var body: some View {
        ScrollView() {
            VStack() {
                VStack(alignment: .leading) {
                    Text("City name")
                        .font(.custom("Inter-Bold", size: 20))
                    Text("\(Date().formatted(.dateTime.day().month()))")
                }
                HStack(alignment: .center) {
                    Image("rainyImage")
                        .resizable()
                        .scaledToFit()
                    VStack {
                        Text(viewModel.temperature)
                            .font(.custom("Inter-Bold", size: 43))
                            .padding(0)
                        Text(viewModel.type)
                    }
                }
                Text(viewModel.windSpeed)
                Text(viewModel.rainFall)
            }.frame(maxWidth: .infinity)
        }
        .background(Color.mint.ignoresSafeArea())
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
