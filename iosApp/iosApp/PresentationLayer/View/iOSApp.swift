import MultiPlatformLibrary
import SwiftUI

@main
struct iOSApp: App {
    init() {
        SetupHelper.init().setup()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
