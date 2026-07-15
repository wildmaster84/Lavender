package net.minecraft.core;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import net.minecraft.tags.TagKey;

public interface HolderSet<T> {
    List<Holder<T>> holders();

    Stream<Holder<T>> stream();

    boolean contains(Holder<T> holder);

    int size();

    Holder<T> get(int index);

    class Named<T> implements HolderSet<T> {
        private final TagKey<T> key;
        private final List<Holder<T>> holders;

        public Named(TagKey<T> key, List<Holder<T>> holders) {
            this.key = key;
            this.holders = holders;
        }

        public TagKey<T> key() {
            return key;
        }

        @Override
        public List<Holder<T>> holders() {
            return holders;
        }

        @Override
        public Stream<Holder<T>> stream() {
            return holders.stream();
        }

        @Override
        public boolean contains(Holder<T> holder) {
            return holders.contains(holder);
        }

        @Override
        public int size() {
            return holders.size();
        }

        @Override
        public Holder<T> get(int index) {
            return holders.get(index);
        }
    }
}
