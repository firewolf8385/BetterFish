package org.firewolf8385.betterfish.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatUtils {
    private final static int CENTER_PX = 154;

    /**
     * A quicker way to send colored messages to the player in chat.
     * @param player Player
     * @param message Message to be sent.
     */
    public static void chat(Player player, String message) {
        chat((CommandSender) player, message);
    }

    /**
     * A quicker way to send colored messages to a command sender.
     * @param sender Command Sender
     * @param message Message to be sent.
     */
    public static void chat(CommandSender sender, String message) {
        sender.sendMessage(ChatUtils.translate(message));
    }

    /**
     * Send a centered chat message to a player.
     * @param player Player
     * @param message nessage
     */
    public static void centeredChat(Player player, String message) {
        centeredChat((CommandSender) player, message);
    }

    /**
     * Sender a centered chat message to a CommandSender.
     * @param sender Command Sender
     * @param message Message
     */
    public static void centeredChat(CommandSender sender, String message) {
        message = translate(message);

        if(message == null || message.equals("")) sender.sendMessage("");
        message = ChatColor.translateAlternateColorCodes('&', message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()) {
            if(c == '§') {
                previousCode = true;
                continue;
            }
            else if(previousCode == true) {
                previousCode = false;
                if(c == 'l' || c == 'L') {
                    isBold = true;
                    continue;
                }
                else isBold = false;
            }
            else {
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }

        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate) {
            sb.append(" ");
            compensated += spaceLength;
        }
        sender.sendMessage(sb.toString() + message);
    }

    /**
     * Translate a String to a colorful String.
     * @param message Message to translate.
     * @return Translated Message.
     */
    public static String translate(String message) {
            Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");
            Matcher matcher = pattern.matcher(message);

            while (matcher.find()) {
                String color = message.substring(matcher.start(), matcher.end());
                message = message.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
                matcher = pattern.matcher(message);
            }

        return net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Translate an array of strings.
     * @param arr Strings
     * @return array of translated strings.
     */
    public static String[] translate(String[] arr) {
        for(int i = 0; i < arr.length; i++) {
            arr[i] = translate(arr[i]);
        }

        return arr;
    }

}
