package com.concert.ticket.controllers;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.concert.ticket.models.Event;
import com.concert.ticket.models.Participant;
import com.concert.ticket.models.Place;
import com.concert.ticket.models.Ticket;
import com.concert.ticket.repos.EventRepository;
import com.concert.ticket.repos.ParticipantRepository;
import com.concert.ticket.repos.PlaceRepository;
import com.concert.ticket.repos.TicketRepository;

import java.util.Optional;

@Controller
public class MainController {
	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private ParticipantRepository participantRepository;
	@Autowired
	private TicketRepository ticketRepository;

	@GetMapping("/ticket")
	public String tickets(Model model) {
		Iterable<Ticket> tickets = ticketRepository.findAll();
		model.addAttribute("tickets", tickets);
		return "ticket";
	}
	@GetMapping("/ticket/add")
	public String ticketAddGet(Model model) {
		Ticket ticket = new Ticket();
		Iterable<Event> events = eventRepository.findAll();
		Iterable<Participant> participants = participantRepository.findAll();
		model.addAttribute("ticket", ticket);
		model.addAttribute("events", events);
		model.addAttribute("participants", participants);
		return "ticket-add";
	}
	@PostMapping("/ticket/add")
	public String ticketAddPost(@ModelAttribute Ticket ticket) {
		Optional<Event> event = eventRepository.findById(ticket.getEvent().getId());
		ticket.setEvent(event.orElseGet(Event::new));
		Optional<Participant> participant = participantRepository.findById(ticket.getParticipant().getId());
		ticket.setParticipant(participant.orElseGet(Participant::new));
		ticketRepository.save(ticket);
		return "redirect:/ticket";
	}
	@GetMapping("/ticket/{id}/edit")
	public String ticketEditGet(@PathVariable (value = "id") Long id,
							   Model model) {
		if (!ticketRepository.existsById(id)) {
			return "redirect:/ticket";
		}
		Optional<Ticket> ticket = ticketRepository.findById(id);
		Iterable<Event> events = eventRepository.findAll();
		Iterable<Participant> participants = participantRepository.findAll();
		model.addAttribute("ticket", ticket.orElseGet(Ticket::new));
		model.addAttribute("events", events);
		model.addAttribute("participants", participants);
		return "ticket-edit";
	}
	@PostMapping("/ticket/{id}/edit")
	public String ticketEditPost(@ModelAttribute Ticket ticket) {
		ticketRepository.save(ticket);
		return "redirect:/ticket";
	}
	@PostMapping("/ticket/{id}/delete")
	public String ticketDeletePost(@PathVariable (value = "id") Long id) {
		Optional<Ticket> ticket = ticketRepository.findById(id);
		ticketRepository.delete(ticket.orElseGet(Ticket::new));
		return "redirect:/ticket";
	}

	@GetMapping("/event")
	public String events(Model model) {
		Iterable<Event> events = eventRepository.findAll();
		model.addAttribute("events", events);
		return "event";
	}
	@GetMapping("/event/add")
	public String eventAddGet(Model model) {
		Event event = new Event();
		Iterable<Place> places = placeRepository.findAll();
		model.addAttribute("event", event);
		model.addAttribute("places", places);
		return "event-add";
	}
	@PostMapping("/event/add")
	public String eventAddPost(@ModelAttribute @Valid Event event,
							   BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "/event/add";
		Optional<Place> place = placeRepository.findById(event.getPlace().getId());
		event.setPlace(place.orElseGet(Place::new));
		eventRepository.save(event);
		return "redirect:/event";
	}
	@GetMapping("/event/{id}/edit")
	public String eventEditGet(@PathVariable (value = "id") Long id,
							   Model model) {
		if (!eventRepository.existsById(id)) {
			return "redirect:/event";
		}
		Optional<Event> event = eventRepository.findById(id);
		Iterable<Place> places = placeRepository.findAll();
		model.addAttribute("event", event.orElseGet(Event::new));
		model.addAttribute("places", places);
		return "event-edit";
	}
	@PostMapping("/event/{id}/edit")
	public String eventEditPost(@ModelAttribute Event event) {
		eventRepository.save(event);
		return "redirect:/event";
	}
	@PostMapping("/event/{id}/delete")
	public String eventDeletePost(@PathVariable (value = "id") Long id) {
		Optional<Event> event = eventRepository.findById(id);
		eventRepository.delete(event.orElseGet(Event::new));
		return "redirect:/event";
	}

	@GetMapping("/place")
	public String places(Model model) {
		Iterable<Place> places = placeRepository.findAll();
		model.addAttribute("places", places);
		return "place";
	}
	@GetMapping("/place/add")
	public String placeAddGet(Model model) {
		return "place-add";
	}
	@PostMapping("/place/add")
	public String placeAddPost(@RequestParam String name,
							   @RequestParam String address) {
		Place place = new Place(name, address);
		placeRepository.save(place);
		return "redirect:/place";
	}
	@GetMapping("/place/{id}/edit")
	public String placeEditGet(@PathVariable (value = "id") Long id,
							   Model model) {
		if (!placeRepository.existsById(id)) {
			return "redirect:/place";
		}
		Optional<Place> place = placeRepository.findById(id);
		model.addAttribute("place", place.orElseGet(Place::new));
		return "place-edit";
	}
	@PostMapping("/place/{id}/edit")
	public String placeEditPost(@ModelAttribute Place place) {
		placeRepository.save(place);
		return "redirect:/place";
	}
	@PostMapping("/place/{id}/delete")
	public String placeDeletePost(@PathVariable (value = "id") Long id) {
		Optional<Place> place = placeRepository.findById(id);
		placeRepository.delete(place.orElseGet(Place::new));
		return "redirect:/place";
	}

	@GetMapping("/participant")
	public String participants(Model model) {
		Iterable<Participant> participants = participantRepository.findAll();
		model.addAttribute("participants", participants);
		return "participant";
	}
	@GetMapping("/participant/add")
	public String participantAddGet() {
		return "participant-add";
	}
	@PostMapping("/participant/add")
	public String participantAddPost(@RequestParam String surname,
									 @RequestParam String name,
									 @RequestParam String phoneNumber) {
		Participant participant = new Participant(name, surname, phoneNumber);
		participantRepository.save(participant);
		return "redirect:/participant";
	}
	@GetMapping("/participant/{id}/edit")
	public String participantEditGet(@PathVariable (value = "id") Long id,
									 Model model) {
		if (!participantRepository.existsById(id)) {
			return "redirect:/participant";
		}
		Optional<Participant> participant = participantRepository.findById(id);
		model.addAttribute("participant", participant.orElseGet(Participant::new));
		return "participant-edit";
	}
	@PostMapping("/participant/{id}/edit")
	public String participantEditPost(@ModelAttribute Participant participant) {
		participantRepository.save(participant);
		return "redirect:/participant";
	}
	@PostMapping("/participant/{id}/delete")
	public String participantDeletePost(@PathVariable (value = "id") Long id) {
		Optional<Participant> participant = participantRepository.findById(id);
		participantRepository.delete(participant.orElseGet(Participant::new));
		return "redirect:/participant";
	}


}
