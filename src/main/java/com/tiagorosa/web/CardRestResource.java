package com.tiagorosa.web;

import com.tiagorosa.model.Card;
import com.tiagorosa.service.CardService;
import lombok.AllArgsConstructor;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Path("/cards")
public class CardRestResource {

    private final CardService cardService;

    @POST
    public Response create(Card card) {
        cardService.create(card);
        return Response.noContent().build();
    }

    @PUT
    @Path("/{id}")
    public Response update(Long id, Card card) {
        return Optional.ofNullable(cardService.update(id, card))
                .map((c) -> Response.ok(c))
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @GET
    public Response list() {
        return Optional.ofNullable(cardService.list())
                .map((c) -> Response.ok(c))
                .orElse(Response.status(Response.Status.NOT_FOUND))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(Long id) {
        cardService.delete(id);
        return Response.noContent().build();
    }
}