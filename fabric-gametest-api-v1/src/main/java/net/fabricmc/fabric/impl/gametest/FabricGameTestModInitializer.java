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

package net.fabricmc.fabric.impl.gametest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.entrypoint.EntrypointContainer;
import net.minecraft.gametest.framework.GameTestRegistry;

public final class FabricGameTestModInitializer implements ModInitializer {
	private static final String ENTRYPOINT_KEY = "fabric-gametest";
	private static final Map<Class<?>, String> GAME_TEST_IDS = new HashMap<>();
	private static final Logger LOGGER = LoggerFactory.getLogger(FabricGameTestModInitializer.class);

	@Override
	public void onInitialize() {
		List<EntrypointContainer<Object>> entrypointContainers = FabricLoader.getInstance()
				.getEntrypointContainers(ENTRYPOINT_KEY, Object.class);

		for (EntrypointContainer<Object> container : entrypointContainers) {
			Class<?> testClass = container.getEntrypoint().getClass();
			String modid = container.getProvider().getMetadata().getId();

			if (GAME_TEST_IDS.containsKey(testClass)) {
				throw new UnsupportedOperationException("Test class (%s) has already been registered with mod (%s)".formatted(testClass.getCanonicalName(), modid));
			}

			GAME_TEST_IDS.put(testClass, modid);
			GameTestRegistry.register(testClass);

			LOGGER.debug("Registered test class {} for mod {}", testClass.getCanonicalName(), modid);
		}
	}
}
