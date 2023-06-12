package com.rentaride.carrental.service;

import com.rentaride.carrental.model.rental.Rental;
import com.rentaride.carrental.repository.RentalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RentalService {
    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Transactional
    public void saveRental(Rental rental) {
        rentalRepository.save(rental);
    }

    @Transactional
    public List<Rental> getRentalsByUserId(Long userId) {
        return rentalRepository.findRentalsByUserId(userId);
    }

    public Optional<Rental> getRentalByCarLicencePlate(String carPlate) {
        return rentalRepository.findByCarLicencePlate(carPlate);
    }

    public String sendRentalConfirmationEmail(String name, String carMake, String carModel, double totalPrice, String startDate, String endDate) {
        StringBuilder emailBuilder = new StringBuilder();
        emailBuilder.append("<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n")
                .append("\n")
                .append("<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n")
                .append("\n")
                .append("  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n")
                .append("    <tbody><tr>\n")
                .append("      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n")
                .append("        \n")
                .append("        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n")
                .append("          <tbody><tr>\n")
                .append("            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n")
                .append("                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n")
                .append("                  <tbody><tr>\n")
                .append("                    <td style=\"padding-left:10px\">\n")
                .append("                  \n")
                .append("                    </td>\n")
                .append("                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n")
                .append("                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Car Rental Confirmation</span>\n")
                .append("                    </td>\n")
                .append("                  </tr>\n")
                .append("                </tbody></table>\n")
                .append("              </a>\n")
                .append("            </td>\n")
                .append("          </tr>\n")
                .append("        </tbody></table>\n")
                .append("        \n")
                .append("      </td>\n")
                .append("    </tr>\n")
                .append("  </tbody></table>\n")
                .append("  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n")
                .append("    <tbody><tr>\n")
                .append("      <td height=\"30\"><br></td>\n")
                .append("    </tr>\n")
                .append("    <tr>\n")
                .append("      <td width=\"10\" valign=\"middle\"><br></td>\n")
                .append("      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n")
                .append("        \n")
                .append("            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi ")
                .append(name)
                .append(",</p>\n")
                .append("            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Congratulations! You have successfully rented a car.</p>\n")
                .append("            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Here are the details of your rental:</p>\n")
                .append("            <ul style=\"list-style-type:disc;Margin:0 0 20px 20px;font-size:19px;line-height:25px;color:#0b0c0c\">\n")
                .append("              <li><strong>Rented car: </strong>")
                .append(carMake)
                .append(" ")
                .append(carModel)
                .append("</li>\n")
                .append("              <li><strong>Total Price: </strong>")
                .append(totalPrice)
                .append("$ </li>\n")
                .append("              <li><strong>Rental Period: </strong> ")
                .append(startDate)
                .append(" to ")
                .append(endDate)
                .append("</li>\n")
                .append("            </ul>\n")
                .append("            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">We hope you enjoy your rental experience. If you have any questions or need further assistance, feel free to contact us.</p>\n")
                .append("            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Thank you for choosing our car rental service.</p>\n")
                .append("            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Best regards,</p>\n")
                .append("            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Your Car Rental Team</p>\n")
                .append("\n")
                .append("      </td>\n")
                .append("      <td width=\"10\" valign=\"middle\"><br></td>\n")
                .append("    </tr>\n")
                .append("    <tr>\n")
                .append("      <td height=\"30\"><br></td>\n")
                .append("    </tr>\n")
                .append("  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n")
                .append("\n")
                .append("</div></div>");

        return emailBuilder.toString();
    }
}
