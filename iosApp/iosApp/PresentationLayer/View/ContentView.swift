import SwiftUI
import core

struct ContentView: View {
    let greet = "Test"
    @StateObject var viewModel = MainViewModel()
    var body: some View {
        ScrollView {
            VStack {
                Text("City name")
                Text("День недели")
                Text("Температура \(viewModel.temperature)")
                Text(viewModel.windSpeed)
                Text(viewModel.rainFall)
                Text(viewModel.type)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
