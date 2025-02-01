package com.example.myproj;

import com.example.myproj.models.User;
import com.example.myproj.models.Event;
import com.example.myproj.repo.UserRepository;
import com.example.myproj.repo.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@SessionAttributes("loggedInUser")
public class BookController {

    private final UserRepository userRepository;
    private final EventRepository eventRepository;

    public BookController(UserRepository userRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/create")
    public String createPage(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "Creator".equals(loggedInUser.getRole())) {
            return "create";
        }
        return "redirect:/";
    }

    @PostMapping("/create-event")
    @ResponseBody
    public ResponseEntity<?> createEvent(@RequestBody Map<String, String> eventData, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser == null || !"Creator".equals(loggedInUser.getRole())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Unauthorized access"));
        }

        String eventName = eventData.get("eventName");
        String eventDate = eventData.get("eventDate");
        String sportType = eventData.get("sportType");

        if (eventName == null || eventDate == null || sportType == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Missing event data"));
        }

        try {
            Event event = new Event();
            event.setEventName(eventName);
            event.setCreaterFullname(loggedInUser.getFirstName() + " " + loggedInUser.getLastName());
            event.setEventDate(LocalDate.parse(eventDate)); // Преобразование строки в LocalDate
            event.setSportType(sportType);

            // Сохраняем событие в базе данных
            eventRepository.save(event);

            // Создаем сообщение для Telegram с URL-кодированием
            String message = String.format("🎉 New Event Created! 🎉\n\nEvent: %s\nDate: %s\nSport: %s\nCreator: %s",
                    eventName, eventDate, sportType, event.getCreaterFullname());
            sendTelegramNotification(message);

            return ResponseEntity.ok(Map.of("message", "Event created successfully!"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Invalid date format. Use YYYY-MM-DD."));
        }
    }

    private void sendTelegramNotification(String message) {
        String botToken = "7438167021:AAH3wddcmo3FZfGo2lEBG_hLOOJ9I3F5Tqs"; // Ваш токен
        String chatId = "-1002390582387"; // ID вашего канала

        try {
            // URL кодирование сообщения
            String encodedMessage = URLEncoder.encode(message, StandardCharsets.UTF_8.toString());

            String url = String.format("https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                    botToken, chatId, encodedMessage);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.getResponseCode(); // Получаем код ответа, чтобы отправка прошла
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/events")
    public String eventsPage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);
            List<Event> events = (List<Event>) eventRepository.findAll(); // Получаем все события из базы данных
            model.addAttribute("events", events);
            return "event"; // Страница для отображения всех событий
        }
        return "redirect:/";
    }

    @GetMapping("/")
    public String home(HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            if ("admin".equals(loggedInUser.getRole())) {
                return "redirect:/admin";
            } else if ("athlete".equals(loggedInUser.getRole())) {
                return "redirect:/user";
            }
        }
        return "index";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials, HttpSession session) {
        String nickname = credentials.get("nickname");
        String password = credentials.get("password");

        if (nickname == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("error", "Missing nickname or password"));
        }

        Optional<User> userOptional = userRepository.findByNickname(nickname);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getPassword().equals(password)) {
                session.setAttribute("loggedInUser", user);
                return ResponseEntity.ok(Map.of(
                        "first_name", user.getFirstName(),
                        "last_name", user.getLastName(),
                        "role", user.getRole(),
                        "profile_picture_url", user.getProfilePictureUrl()
                ));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid password"));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
    }

    @GetMapping("/admin")
    public String adminPanel(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null && "admin".equals(loggedInUser.getRole())) {
            model.addAttribute("adminName", loggedInUser.getFirstName() + " " + loggedInUser.getLastName());

            // Получаем всех пользователей из базы данных
            List<User> users = userRepository.findAll();
            model.addAttribute("users", users);  // Добавляем в модель

            return "admin";  // Страница admin.html
        }
        return "redirect:/";  // Если доступ запрещен, перенаправляем на главную
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @GetMapping("/go_home")
    public String go_home(HttpSession session) {
        return "index"; // Главная страница
    }

    @GetMapping("/user")
    public String userPage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);
            return "user"; // Страница user.html
        }
        return "redirect:/";
    }

    @GetMapping("/vse")
    public String vsePage(HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        if (loggedInUser != null) {
            model.addAttribute("user", loggedInUser);

            // Получаем всех пользователей из базы данных
            List<User> users = userRepository.findAll();
            model.addAttribute("users", users);  // Добавляем в модель

            return "vse"; // Страница vse.html
        }
        return "redirect:/"; // Если пользователь не авторизован, перенаправляем на главную
    }
    @PostMapping("/update-user")
    @ResponseBody
    public ResponseEntity<?> updateUser(@RequestBody Map<String, String> userDetails, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        if (loggedInUser != null) {
            // Обновляем данные пользователя
            loggedInUser.setFirstName(userDetails.get("first_name"));
            loggedInUser.setLastName(userDetails.get("last_name"));
            loggedInUser.setNickname(userDetails.get("nickname"));
            loggedInUser.setPhoneNumber(userDetails.get("phone_number"));
            loggedInUser.setProfilePictureUrl(userDetails.get("profile_picture_url"));

            // Сохраняем обновленные данные в базе данных
            userRepository.save(loggedInUser);

            // Обновляем данные в сессии
            session.setAttribute("loggedInUser", loggedInUser);

            return ResponseEntity.ok(Map.of("message", "User details updated successfully!"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "User not authorized"));
    }

    /**
     * Удаление пользователя.
     */
    @PostMapping("/delete-user/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "User not found"));
    }
}