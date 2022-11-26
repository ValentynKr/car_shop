package com.epam.lab.shop.utility;

import com.epam.lab.shop.constant.Constant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

public class AvatarManager {

    private static final Logger LOGGER = Logger.getLogger(AvatarManager.class.getName());

    public void getAndSaveAvatarOnServer(HttpServletRequest request, int userId) {
        Part filePart = getPartWithAvatarFromRequest(request);
        if (filePart == null) {
            return;
        }
        File userAvatar = createFileWithUserAvatar(request, userId);
        writeFileInImageDirectory(filePart, userAvatar);
    }

    private void writeFileInImageDirectory(Part filePart, File avatar) {
        try (OutputStream out = new FileOutputStream(avatar);
             InputStream fileContent = filePart.getInputStream()) {
            int read;
            final byte[] bytes = new byte[1024];
            while ((read = fileContent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
        } catch (IOException exception) {
            LOGGER.severe(Constant.IO_EXCEPTION_THROWN_WHILE_WRITING_FILE + exception);
        }
    }

    private File createFileWithUserAvatar(HttpServletRequest request, int userId) {
        String fileName = String.format(Constant.FILE_NAME, userId);
        String rootPath = request.getServletContext().getRealPath(Constant.USER_IMAGES_DIRECTORY);
        File directory = new File(rootPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        return new File(directory.getAbsolutePath() + File.separator + fileName);
    }

    private Part getPartWithAvatarFromRequest(HttpServletRequest request) {
        Part filePart = null;
        try {
            filePart = request.getPart(Constant.AVATAR);
        } catch (IOException | ServletException exception) {
            LOGGER.severe(Constant.IO_EXCEPTION_THROWN_WHILE_GETTING_PART + exception);
        }
        return filePart;
    }
}