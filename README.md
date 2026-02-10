PROJECT OVERVIEW
This project implements a Weather API using Spring Boot that retrieves real-time weather data from the OpenWeather API instead of generating local data. The application demonstrates third-party API integration, Redis caching, environment variable usage, and clean backend architecture.

SYSTEM FLOW
1. User sends a request with a city name.
2. The application checks Redis cache.
3. If cached data exists, it is returned.
4. If not cached:
   - City name is converted to latitude and longitude using OpenWeather Geocoding API.
   - Coordinates are used to fetch current weather.
   - Data is mapped to a clean DTO.
   - Result is stored in Redis with a 12-hour TTL.
   - Response is returned to the user.
CONTROLLER LAYER
Handles HTTP requests, input validation, and response output. No business logic is placed here.

SERVICE LAYER
Contains the application logic, manages caching, calls external APIs, and maps responses.

CLIENT LAYER
Responsible for making HTTP requests to OpenWeather using Spring RestClient.

DTO LAYER
Separates raw external API responses from internal API responses.

ENVIRONMENT VARIABLES
API keys are stored securely using environment variables to avoid hardcoding sensitive information.

REDIS CACHING
Redis is used as an in-memory cache with:
- Key format: weather:v2:<city>
- TTL: 12 hours
- Versioned keys to avoid serialization conflicts
