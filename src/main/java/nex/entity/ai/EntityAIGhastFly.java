/*
 * NetherEx
 * Copyright (c) 2016-2018 by MineEx
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

package nex.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.monster.EntityGhast;

import java.util.Random;

public class EntityAIGhastFly extends EntityAIBase
{
    private final EntityGhast ghast;

    public EntityAIGhastFly(EntityGhast ghast)
    {
        this.ghast = ghast;
        setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityMoveHelper moveHelper = ghast.getMoveHelper();

        if(!moveHelper.isUpdating())
        {
            return true;
        }
        else
        {
            double d0 = moveHelper.getX() - ghast.posX;
            double d1 = moveHelper.getY() - ghast.posY;
            double d2 = moveHelper.getZ() - ghast.posZ;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            return d3 < 1.0D || d3 > 3600.0D;
        }
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return false;
    }

    @Override
    public void startExecuting()
    {
        Random rand = ghast.getRNG();
        double d0 = ghast.posX + (double) ((rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        double d1 = ghast.posY + (double) ((rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        double d2 = ghast.posZ + (double) ((rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
        ghast.getMoveHelper().setMoveTo(d0, d1, d2, 1.0D);
    }
}
