package example

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get

@Controller("/hello")
class HelloController implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Get("/")
    String index(String name) {
        return "Hello ${name}!"
    }

    @Override
    APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
        context.logger.log "received in groovy: $request.body"
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent()
        response.statusCode = 200
        response.body = index(request.pathParameters.name ?: '')
        return response
    }

}