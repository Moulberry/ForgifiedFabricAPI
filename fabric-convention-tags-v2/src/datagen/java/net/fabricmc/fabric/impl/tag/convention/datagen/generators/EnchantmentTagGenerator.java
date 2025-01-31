/*
 * Copyright (c) 2016, 2017, 2018, 2019 FabricMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.fabricmc.fabric.impl.tag.convention.datagen.generators;

import java.util.concurrent.CompletableFuture;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalEnchantmentTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.enchantment.Enchantments;

public final class EnchantmentTagGenerator extends FabricTagProvider.EnchantmentTagProvider {
	public EnchantmentTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider registries) {
		tag(ConventionalEnchantmentTags.INCREASE_BLOCK_DROPS)
				.add(Enchantments.FORTUNE);
		tag(ConventionalEnchantmentTags.INCREASE_ENTITY_DROPS)
				.add(Enchantments.LOOTING);
		tag(ConventionalEnchantmentTags.WEAPON_DAMAGE_ENHANCEMENTS)
				.add(Enchantments.SHARPNESS)
				.add(Enchantments.SMITE)
				.add(Enchantments.BANE_OF_ARTHROPODS)
				.add(Enchantments.POWER)
				.add(Enchantments.IMPALING);
		tag(ConventionalEnchantmentTags.ENTITY_SPEED_ENHANCEMENTS)
				.add(Enchantments.SOUL_SPEED)
				.add(Enchantments.SWIFT_SNEAK)
				.add(Enchantments.DEPTH_STRIDER);
		tag(ConventionalEnchantmentTags.ENTITY_AUXILIARY_MOVEMENT_ENHANCEMENTS)
				.add(Enchantments.FEATHER_FALLING)
				.add(Enchantments.FROST_WALKER);
		tag(ConventionalEnchantmentTags.ENTITY_DEFENSE_ENHANCEMENTS)
				.add(Enchantments.PROTECTION)
				.add(Enchantments.BLAST_PROTECTION)
				.add(Enchantments.PROJECTILE_PROTECTION)
				.add(Enchantments.FIRE_PROTECTION)
				.add(Enchantments.RESPIRATION)
				.add(Enchantments.FEATHER_FALLING);

		// Backwards compat with pre-1.21 tags. Done after so optional tag is last for better readability.
		// TODO: Remove backwards compat tag entries in 1.22
		tag(ConventionalEnchantmentTags.ENTITY_SPEED_ENHANCEMENTS)
				.addOptionalTag(ResourceLocation.fromNamespaceAndPath("c", "entity_movement_enhancement"));
		tag(ConventionalEnchantmentTags.ENTITY_DEFENSE_ENHANCEMENTS)
				.addOptionalTag(ResourceLocation.fromNamespaceAndPath("c", "entity_defense_enhancement"));
	}
}
