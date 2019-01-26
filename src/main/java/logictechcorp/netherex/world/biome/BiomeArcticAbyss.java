/*
 * NetherEx
 * Copyright (c) 2016-2019 by LogicTechCorp
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

package logictechcorp.netherex.world.biome;

import com.electronwill.nightconfig.core.Config;
import logictechcorp.libraryex.world.biome.BiomeBlockType;
import logictechcorp.libraryex.world.biome.BiomeInfo;
import logictechcorp.libraryex.world.generation.GenerationStage;
import logictechcorp.libraryex.world.generation.feature.ConfigurableFeatureCluster;
import logictechcorp.libraryex.world.generation.feature.ConfigurableFeatureOre;
import logictechcorp.libraryex.world.generation.feature.ConfigurableFeaturePool;
import logictechcorp.libraryex.world.generation.feature.ConfigurableFeatureScatter;
import logictechcorp.netherex.NetherEx;
import logictechcorp.netherex.entity.monster.EntityBrute;
import logictechcorp.netherex.entity.monster.EntityCoolmarSpider;
import logictechcorp.netherex.entity.monster.EntityWight;
import logictechcorp.netherex.init.NetherExBiomes;
import logictechcorp.netherex.init.NetherExBlocks;
import logictechcorp.netherex.world.biome.info.NetherBiomeInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.Arrays;

public class BiomeArcticAbyss extends BiomeNetherEx
{
    private static final IBlockState FROSTBURN_ICE = NetherExBlocks.FROSTBURN_ICE.getDefaultState();
    private static final IBlockState ICY_NETHERRACK = NetherExBlocks.ICY_NETHERRACK.getDefaultState();

    public BiomeArcticAbyss()
    {
        super(NetherEx.instance, new BiomeProperties("Arctic Abyss").setTemperature(0.0F).setRainfall(0.0F).setRainDisabled(), "arctic_abyss");
        this.topBlock = FROSTBURN_ICE;
        this.fillerBlock = ICY_NETHERRACK;
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityGhast.class, 50, 1, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityPigZombie.class, 25, 1, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityCoolmarSpider.class, 35, 1, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityWight.class, 100, 1, 4));
        this.spawnableMonsterList.add(new Biome.SpawnListEntry(EntityBrute.class, 15, 1, 1));
    }

    @Override
    public BiomeInfo getInfo()
    {
        return new Info();
    }

    private class Info extends NetherBiomeInfo
    {
        public Info()
        {
            super(NetherExBiomes.ARCTIC_ABYSS.getRegistryName(), 2, true, true);
        }

        @Override
        public Config getAsConfig()
        {
            this.getBiomeBlock(BiomeBlockType.FLOOR_TOP_BLOCK, FROSTBURN_ICE);
            this.getBiomeBlock(BiomeBlockType.FLOOR_FILLER_BLOCK, ICY_NETHERRACK);
            this.getBiomeBlock(BiomeBlockType.WALL_BLOCK, ICY_NETHERRACK);
            this.getBiomeBlock(BiomeBlockType.CEILING_FILLER_BLOCK, ICY_NETHERRACK);
            this.getBiomeBlock(BiomeBlockType.CEILING_BOTTOM_BLOCK, ICY_NETHERRACK);
            this.getBiomeBlock(BiomeBlockType.OCEAN_BLOCK, MAGMA);
            this.getEntities(EnumCreatureType.MONSTER).addAll(new ArrayList<>(Arrays.asList(
                    new Biome.SpawnListEntry(EntityGhast.class, 50, 1, 4),
                    new Biome.SpawnListEntry(EntityPigZombie.class, 25, 1, 4),
                    new Biome.SpawnListEntry(EntityCoolmarSpider.class, 35, 1, 4),
                    new Biome.SpawnListEntry(EntityWight.class, 100, 1, 4),
                    new Biome.SpawnListEntry(EntityBrute.class, 15, 1, 1)
            )));
            this.getFeatures(GenerationStage.PRE_DECORATE).addAll(new ArrayList<>(Arrays.asList(
                    new ConfigurableFeatureScatter(5, 1.0D, true, 4, 124, NetherExBlocks.BLUE_FIRE.getDefaultState(), FROSTBURN_ICE, ConfigurableFeatureScatter.Placement.ON_GROUND),
                    new ConfigurableFeatureCluster(10, 1.0D, true, 4, 124, Blocks.GLOWSTONE.getDefaultState(), ICY_NETHERRACK, EnumFacing.DOWN),
                    new ConfigurableFeatureCluster(10, 1.0D, false, 1, 128, Blocks.GLOWSTONE.getDefaultState(), ICY_NETHERRACK, EnumFacing.DOWN),
                    new ConfigurableFeaturePool(2, 0.125, false, 36, 96, NetherExBlocks.ICHOR.getDefaultState(), FROSTBURN_ICE)
            )));
            this.getFeatures(GenerationStage.ORE).addAll(new ArrayList<>(Arrays.asList(
                    new ConfigurableFeatureOre(16, 1.0D, false, 10, 108, NetherExBlocks.QUARTZ_ORE.getDefaultState(), ICY_NETHERRACK, 14),
                    new ConfigurableFeatureOre(16, 1.0D, false, 10, 108, NetherExBlocks.RIME_ORE.getDefaultState(), ICY_NETHERRACK, 7)
            )));
            return super.getAsConfig();
        }
    }
}
