/* 
 * Copyright (C) 2016 Narayan G. Maharjan <me@ngopal.com.np>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package np.com.ngopal.particle.cloud.api.v1;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.HashMap;
import java.util.Map;
import lombok.*;
import np.com.ngopal.particle.cloud.AuthClient;
import np.com.ngopal.particle.cloud.api.AbstractAPI;
import np.com.ngopal.particle.cloud.api.exception.APIException;
import np.com.ngopal.particle.cloud.api.resources.CustomerResource;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;

/**
 *
 * @author NGM
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class APIv1 extends AbstractAPI {

    private String version = "v1";

    private String accessToken;

    @Getter(AccessLevel.NONE)
    @Setter
    private CustomerResource customerResource;

    public APIv1(AuthClient client) {
        this.client = client;
        this.customerResource = customerResource;
    }

    public APIv1(String token) {
        isAccessToken = true;
        this.accessToken = token;
        this.customerResource = customerResource;
    }

    public String getRestUrl() {
        return String.format("%s://%s/%s", getSchema(), getHost(), getVersion());
    }

    public void d(String[] args) throws UnirestException {
        HttpResponse<JsonNode> jsonResponse = Unirest.post("https://api.particle.io/oauth/token")
                .basicAuth("marcopolo-web-x-5662", "3c9ae65f71a011505dd9d746e5b9a725d34b717b")
                .header("accept", "application/json")
                .field("grant_type", "password")
                .field("username", "brian.quinn@cbts.net")
                .field("password", "changemeCBTS!")
                .asJson();
    }

    @Override
    public CustomerResource customers() {
        return this.customerResource;
    }

}
