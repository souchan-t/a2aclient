package souchan.a2a.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.Test;

class AgentCardTest {

    final static String sampleJson = """
            {
              "name": "GeoSpatial Route Planner Agent",
              "description": "Provides advanced route planning, traffic analysis, and custom map generation services. This agent can calculate optimal routes, estimate travel times considering real-time traffic, and create personalized maps with points of interest.",
              "url": "https://georoute-agent.example.com/a2a/v1",
              "provider": {
                "organization": "Example Geo Services Inc.",
                "url": "https://www.examplegeoservices.com"
              },
              "iconUrl": "https://georoute-agent.example.com/icon.png",
              "version": "1.2.0",
              "documentationUrl": "https://docs.examplegeoservices.com/georoute-agent/api",
              "capabilities": {
                "streaming": true,
                "pushNotifications": true,
                "stateTransitionHistory": false
              },
              "securitySchemes": {
                "google": {
                  "type": "openIdConnect",
                  "openIdConnectUrl": "https://accounts.google.com/.well-known/openid-configuration"
                }
              },
              "security": [{ "google": ["openid", "profile", "email"] }],
              "defaultInputModes": ["application/json", "text/plain"],
              "defaultOutputModes": ["application/json", "image/png"],
              "skills": [
                {
                  "id": "route-optimizer-traffic",
                  "name": "Traffic-Aware Route Optimizer",
                  "description": "Calculates the optimal driving route between two or more locations, taking into account real-time traffic conditions, road closures, and user preferences (e.g., avoid tolls, prefer highways).",
                  "tags": ["maps", "routing", "navigation", "directions", "traffic"],
                  "examples": [
                    "Plan a route from '1600 Amphitheatre Parkway, Mountain View, CA' to 'San Francisco International Airport' avoiding tolls.",
                    "{\\"origin\\": {\\"lat\\": 37.422, \\"lng\\": -122.084}, \\"destination\\": {\\"lat\\": 37.7749, \\"lng\\": -122.4194}, \\"preferences\\": [\\"avoid_ferries\\"]}"
                  ],
                  "inputModes": ["application/json", "text/plain"],
                  "outputModes": [
                    "application/json",
                    "application/vnd.geo+json",
                    "text/html"
                  ]
                },
                {
                  "id": "custom-map-generator",
                  "name": "Personalized Map Generator",
                  "description": "Creates custom map images or interactive map views based on user-defined points of interest, routes, and style preferences. Can overlay data layers.",
                  "tags": ["maps", "customization", "visualization", "cartography"],
                  "examples": [
                    "Generate a map of my upcoming road trip with all planned stops highlighted.",
                    "Show me a map visualizing all coffee shops within a 1-mile radius of my current location."
                  ],
                  "inputModes": ["application/json"],
                  "outputModes": [
                    "image/png",
                    "image/jpeg",
                    "application/json",
                    "text/html"
                  ]
                }
              ],
              "supportsAuthenticatedExtendedCard": true
            }
            """;

    @Test
    void desirialize() throws JsonMappingException, JsonProcessingException {
        final var oMapper = new ObjectMapper();
        oMapper.registerModule(new Jdk8Module());

        var agentCard = oMapper.readValue(sampleJson, AgentCard.class);

    }

}