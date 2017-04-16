/*
 * NetherEx
 * Copyright (c) 2016-2017 by LogicTechCorp
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation version 3 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package nex.trade;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import nex.NetherEx;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class TradeListManager
{
    private static final Gson GSON = new Gson();

    private static final Logger LOGGER = LogManager.getLogger("NetherEx|TradeListManager");

    public static void init(File file)
    {
        try
        {
            LOGGER.info("Attempting to copy the Pigtificate Trade List to the config folder.");

            if(file.createNewFile())
            {
                try
                {
                    InputStream inStream = NetherEx.class.getResourceAsStream("/assets/nex/pigtificate_trade_list.json");
                    FileWriter writer = new FileWriter(file);
                    IOUtils.copy(inStream, writer);
                    writer.close();
                    inStream.close();
                }
                catch(Exception e)
                {
                    LOGGER.fatal("The attempt to copy the Pigtificate Trade List was unsuccessful.");
                    LOGGER.fatal(e);
                }

                LOGGER.info("The Pigtificate Trade List was successfully copied to config folder.");
            }
            else
            {
                LOGGER.info("The Pigtificate Trade List is already located in the config folder.");
            }
        }
        catch(IOException e)
        {
            LOGGER.fatal("The attempt to copy the Pigtificate Trade List to the config folder was unsuccessful.");
            LOGGER.fatal(e);
        }

        try
        {
            String jsonText = Files.toString(file, Charsets.UTF_8);
            TradeList list = GSON.fromJson(jsonText, TradeList.class);

            System.out.println(list.getName());
            System.out.println(list.getProfessions());
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
