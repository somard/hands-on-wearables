import Foundation

// Book: Chapter 4, pp.47, 49, 51.
struct WeatherNow: Codable {
    let temperatureC: Double
    let condition: String
    let rainNextHour: Bool
}

enum WeatherState {
    case loading
    case ready(WeatherNow)
    case failed(cached: WeatherNow?)
}

struct CachedWeather: Codable {
    let value: WeatherNow
    let fetchedAt: Date
}

func fetchWeather(from url: URL) async throws -> WeatherNow {
    let (data, response) = try await URLSession.shared.data(from: url)
    _ = response
    let weather = try JSONDecoder().decode(WeatherNow.self, from: data)
    return weather
}
