/*
 * Copyright (c) 2018
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package jsettlers.algorithms.simplebehaviortree.nodes;

import java8.util.Optional;
import jsettlers.algorithms.simplebehaviortree.Composite;
import jsettlers.algorithms.simplebehaviortree.NodeStatus;
import jsettlers.algorithms.simplebehaviortree.Tick;

import static java8.util.stream.StreamSupport.stream;

public class DynamicGuardSelector<T> extends Composite<T> {

	public DynamicGuardSelector(Guard<T>[] childrenGuards) {
		super(childrenGuards);
	}

	@Override
	protected NodeStatus onTick(Tick<T> tick) {
		Optional<Guard<T>> childToRunOptional = stream(children).map(node -> (Guard<T>) node).filter(guard -> guard.checkGuardCondition(tick)).findFirst();

		if (!childToRunOptional.isPresent()) {
			return NodeStatus.FAILURE;
		}

		Guard<T> childToRun = childToRunOptional.get();

		return childToRun.execute(tick);
	}

	@Override
	protected void onClose(Tick<T> tick) {
		stream(children).forEach(guard -> guard.close(tick));
	}
}
