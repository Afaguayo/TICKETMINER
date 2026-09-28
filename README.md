# TICKETMINER

A ticket-sales system for events in El Paso venues (Sun Bowl Stadium, Don Haskins Center, Magoffin Auditorium, San Jacinto Plaza and Centennial Plaza), written in Java as a console app. Customers browse events and buy or cancel tickets. Administrators create and cancel events, track revenue and generate invoices. A load-test mode simulates up to a million automated purchases.

Team project (Group 3) for Advanced Object-Oriented Programming, Programming Assignment 5, by Christian Garcia, Javier Aranda, Angel Aguayo and Caleb Lopez.

## Run it

Needs a JDK (Java 8 or newer).

```bash
javac *.java
java RunTicket
```

It loads customers from `CustomerListPA5.csv` and events from `EventListPA5.csv`, and writes updated copies (`UpdatedCustomerList.csv`, `UpdatedEventList.csv`) as tickets sell.

## Features

**Customers** log in with their username and password, then:
- browse events by type (sport, concert, festival) or look one up by ID
- buy tickets in any of five tiers (VIP, Gold, Silver, Bronze, General Admission), each with its own price and share of the venue's seats
- cancel a purchase and get a refund

**TicketMiner members** get 10% off. Every order pays a $2.50 convenience fee plus 0.5% service and 0.75% charity fees, and the app tracks how much each member has saved.

**Administrators** can:
- look up events by ID or name, or list all of them
- create new events at any of the five venues, or cancel one
- see how much the company earned from one event or from all of them (fees, discounts and revenue by tier)
- generate a customer's invoice summary (saved to [`Invoices/`](Invoices))
- list all of a customer's purchases

**Auto-purchase test:** replays scripted orders from `AutoPurchase100.csv` through `AutoPurchase1M.csv` (100 to 1,000,000 customers) to check correctness and speed at scale.

Every login, purchase and cancellation is written to `event_log.txt` by `ActionLogger`.

## Design

- **Inheritance:** abstract `Event` with `Sport`, `Concert` and `Festival` subclasses. Abstract `Venue` with `Stadium`, `Arena`, `Auditorium` and `OpenAir` subclasses. Each venue type sets its own capacity and seating split.
- **Strategy pattern:** `TicketPricingStrategy`, with `RegularPricingStrategy` and `MemberPricingStrategy` choosing the price at checkout.
- **Singleton pattern:** `customerCSV` and `eventCSV`, so every part of the program writes to the same output files.
- **Docs:** full Javadoc in [`javadoc/`](javadoc), plus UML class, state and use-case diagrams in [`Diagram/`](Diagram).
- **Tests:** JUnit 4 tests for administrator actions in `AdministratorActionsTest.java` (the JUnit jars are in [`lib/`](lib)).

The lab report, plan of action and final presentation are in the repo as PDFs.
